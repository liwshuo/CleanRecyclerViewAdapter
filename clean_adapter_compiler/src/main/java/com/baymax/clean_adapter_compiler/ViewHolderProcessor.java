package com.baymax.clean_adapter_compiler;

import com.baymax.clean_adapter_annotation.ViewHolderFactory;
import com.google.auto.common.MoreTypes;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.ElementKindVisitor6;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.baymax.clean_adapter_annotation.ViewHolderFactory")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ViewHolderProcessor extends AbstractProcessor {

    private Filer filer;
    private Map<String, List<TypeName>> categoryTypeNameMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementSet = roundEnv.getElementsAnnotatedWith(ViewHolderFactory.class);
        if (elementSet.isEmpty()) {
            return false;
        }
        for (Element element : elementSet) {
            ViewHolderFactory factory = element.getAnnotation(ViewHolderFactory.class);
            final String category = factory.category();
            element.accept(
                    new ElementKindVisitor6<Void, Void>() {
                        @Override
                        public Void visitTypeAsClass(TypeElement e, Void aVoid) {
                            DeclaredType type = MoreTypes.asDeclared(e.asType());
                            TypeName typeName = TypeName.get(type);
                            if (!categoryTypeNameMap.containsKey(category)) {
                                categoryTypeNameMap.put(category, new ArrayList<TypeName>());
                            }
                            List<TypeName> typeNameList = categoryTypeNameMap.get(category);
                            typeNameList.add(typeName);

                            return null;
                        }
                    },
                    null);
        }
        for (String category : categoryTypeNameMap.keySet()) {
            final MethodSpec.Builder methodBuilder = createFillViewHolderFactoryListMethodBuilder();
            List<TypeName> typeNameList = categoryTypeNameMap.get(category);
            for (TypeName typeName : typeNameList) {
                methodBuilder
                        .addStatement("factoryList.add(new $1T())", typeName);
            }
            methodBuilder.addStatement("return factoryList");
            TypeSpec clazz = TypeSpec.classBuilder(category + "ViewHolderFactoryListCreator")
                    .addModifiers(PUBLIC, FINAL)
                    .addMethod(methodBuilder.build())
                    .build();
            JavaFile file = JavaFile.builder("com.baymax.viewholder", clazz).build();
            try {
                file.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private MethodSpec.Builder createFillViewHolderFactoryListMethodBuilder() {
        return MethodSpec.methodBuilder("createFactoryList")
                .returns(ParameterizedTypeName.get(ClassName.get(List.class), TypeName.get(Object.class)))
                .addModifiers(PUBLIC, STATIC)
                .addStatement("$1T factoryList = new $2T()", ParameterizedTypeName.get(ClassName.get(List.class), TypeName.get(Object.class)),
                        ParameterizedTypeName.get(ClassName.get(ArrayList.class), TypeName.get(Object.class)));
    }

}
