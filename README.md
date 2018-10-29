# CleanRecyclerViewAdapter
It is easy for you to show many different items in recyclerview。You will not have to write many viewholders in recyclerview adapter, and declare many viewtypes, and try to create and bind them. You only need to write a viewholder and its factory with annotation. Then, everything is done.

## 前言
![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)
![JCenter](https://img.shields.io/badge/%20JCenter%20-1.1.0-5bc0de.svg)

首先，我想先感谢下[MultiType](https://github.com/drakeet/MultiType)这个开源项目，我的部分灵感来源于此。

因为业务的需要，我们可能会需要在一个列表中展示非常多样式的元素，这样的话，我们会需要写很多的viewholder，给每种元素声明一个viewtype，在adapter中写一长串的判断语句来createviewholder。如果要展示的项目样式非常多，那么我们的adapter会非常臃肿，还需要定义非常多的viewtype。当可能要修改、删除、添加一个新的样式的时候，都需要在adapter中做修改，违反了对修改关闭的原则。

项目的目的在于将viewholder和adapter解耦，减轻adapter的负担，让删除、添加viewholder变得简单。

在设计时，考虑到对现有代码的改动成本，目前大多数情况下，只需要让现有的viewHolder改自继承BaseCleanViewHolder，并创建与数据类型一一对应的ViewHolderFactory即可。

## 如何引用
在项目的build.gradle文件中的dependicies中添加以下依赖。

```groovy
implementation 'com.baymax.clean_adapter:clean_adapter:0.0.3'
implementation 'com.baymax.clean_adapter:clean_adapter_annotation:0.0.3'
annotationProcessor 'com.baymax.clean_adapter:clean_adapter_compiler:0.0.3'
```

## 如何使用
### 创建ViewHolder
所有的ViewHolder都需要继承自BaseCleanViewHolder，并实现onBindViewHolder方法。

```java
public class AppleViewHolder extends BaseCleanViewHolder<Fruit> {
    private TextView fruitName;

    public AppleViewHolder(ViewGroup parent, MarketInfo marketInfo) {
        super(parent, R.layout.layout_apple_viewholder);
        fruitName = findViewById(R.id.name);
    }

    @Override
    public void onBindViewHolder(Fruit fruit) {
        fruitName.setText(fruit.name);
    }
}

```

BaseCleanViewHolder有两个构造函数。一般来说，重写带ViewGroup参数的构造函数，便于在ViewHolderFactory的抽象类中通过反射来初始化ViewHolder。

```java
/**
 * 一般情况下请重写本构造函数
 * 本构造函数可以确保所有的ViewHolder拥有相同的参数，比如单参数ViewGroup，双参数ViewGroup和ExtraData，
 * 这样可以写一个实现了{@link IViewHolderFactory}的抽象类
 * 实现{@link IViewHolderFactory#create(ViewGroup, Class, Object)}方法，在该方法中使用反射初始化ViewHolder
 * 这样可以减少大量的初始化ViewHolder的代码
 */
public BaseCleanViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
    super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
}
```

而传入View的构造函数则可以用在组合方式展示ViewHolder的情况。

```java
/**
 * 一般在用到组合方式的时候重写本构造函数
 * 如果遇到一个数据结构A包含另外一个数据结构B，而B已经有了自己的ViewHolder的情况下，
 * 可以在A的ViewHolder中使用组合方式引用B的ViewHolder，B的ViewHolder可以利用本构造函数来初始化。
 */
public BaseCleanViewHolder(View itemView) {
    super(itemView);
}
```

BaseCleanViewHolder中还提供了多个便利的方法可以调用。

我们可以保证onBindViewHolder传入的参数，就是范型所指定的数据类型，也可以保证这个参数不为空，所以你可以放心地使用。

### 创建ViewHolderFactory
ViewHolderFactory需要实现IViewHolderFactory接口。

ViewHolderFactory用来将数据结构及其对应的ViewHolder进行绑定。如果一个列表中有N种数据结构，那么就应该有N个ViewHolderFactory与其对应。

如果列表中包含了很多ViewHolder需要展示，那么在create方法中需要写很多ViewHolder初始化的代码，很是臃肿。为了避免这种情况，在确保了所有ViewHolder都拥有相同构造函数及参数的情况下，可以写一个抽象类来通过反射创建ViewHolder。

```java
public abstract class AbstractFoodMaterialViewHolderFactory<Item> implements IViewHolderFactory<Item, MarketInfo> {
    @Override
    public BaseCleanViewHolder create(ViewGroup parent, Class viewHolderClass, MarketInfo marketInfo) {
        BaseCleanViewHolder viewHolder = null;
        try {
            Constructor constructor = viewHolderClass.getConstructor(ViewGroup.class, MarketInfo.class);
            viewHolder = (BaseCleanViewHolder) constructor.newInstance(parent, marketInfo);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (viewHolder == null) {
            try {
                Constructor constructor = viewHolderClass.getConstructor(ViewGroup.class);
                viewHolder = (BaseCleanViewHolder) constructor.newInstance(parent);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (viewHolder == null && BuildConfig.DEBUG) {
            throw new IllegalArgumentException(viewHolderClass + "不是标准构造函数参数，请重写ViewHolderFactory的create方法自行初始化");
        }
        if (viewHolder == null) {
            viewHolder = new BaseCleanViewHolder(parent, R.layout.dummy_view_holder) {
                @Override
                public void onBindViewHolder(Object o) {

                }
            };
        }
        return viewHolder;
    }

}
```

如果一个数据结构只对应一个ViewHolder，那么很简单，如下所示。

```java
@ViewHolderFactory(category = "FoodMaterial")
public class VegetableViewHolderFactory extends AbstractFoodMaterialViewHolderFactory<Vegetable> {
    @Override
    public Class getItemClass() {
        return Vegetable.class;
    }

    @Override
    public Class[] getViewHolderClassList() {
        return new Class[]{
                CabbageViewHolder.class
        };
    }

    @Override
    public Class getViewHolderClass(Vegetable vegetable) {
        return CabbageViewHolder.class;
    }
}
```

如果一个数据结构对应多个ViewHolder，那么需要在getViewHolderClassList方法中返回所有ViewHolder的class的数组，并且在getViewHolderClass方法中，根据条件返回不同的ViewHolder的class。

```java
@ViewHolderFactory(category = "FoodMaterial")
public class FruitViewHolderFactory extends AbstractFoodMaterialViewHolderFactory<Fruit> {
    @Override
    public Class getItemClass() {
        return Fruit.class;
    }

    @Override
    public Class[] getViewHolderClassList() {
        return new Class[]{
                AppleViewHolder.class,
                OrangeViewHolder.class
        };
    }

    @Override
    public Class getViewHolderClass(Fruit fruit) {
        switch (fruit.type) {
            case Fruit.APPLE:
                return AppleViewHolder.class;
            case Fruit.ORANGE:
                return OrangeViewHolder.class;
            default:
                return CleanViewHolderGenerateHelper.DummyCleanViewHolder.class;
        }
    }

}
```

所有的ViewHolderFactory上都必须添加@ViewHolderFactory注解，该注解有一个category参数，在编译时会生成一个名为$categoryViewHolderFactoryListCreator的类，如果有多种不同的category，则会生成多个以category开头的ViewHolderFactoryListCreator类。该类提供了一个静态方法，返回了包含所有相同category的ViewHolderFactory的List。

```java
public final class FoodMaterialViewHolderFactoryListCreator {
  public static List<Object> createFactoryList() {
    List<Object> factoryList = new ArrayList<Object>();
    factoryList.add(new FruitViewHolderFactory());
    factoryList.add(new MeatViewHolderFactory());
    factoryList.add(new FoodMaterialAreaViewHolderFactory());
    factoryList.add(new VegetableViewHolderFactory());
    return factoryList;
  }
}
```

如果我们的ViewHolder根据业务逻辑放在了多个不同的Module中，那么不同Module中的category必须保证不能相同，否则在编译的时候，会出现不同Module中有同名类导致编译不过的问题。

或者虽然在一个Module中，但是想做一个区分。也可以定义不同的category。


### 创建ViewHolderGenerateHelper
在clean_adapter中，有一个名为CleanViewHolderGenerateHelper的类，该类的构造函数接收ViewHolderFactory的List，即我们在上一步编译之后得到的ViewHolderFactoryListCreator静态方法返回的结果。

在上一步中，我们在编译之后会得到一个或者多个ViewHolderFactoryListCreator，假如这些ViewHolderFactory包含的ViewHolder都想在一个列表中展示，那么可以将这些ViewHolderFactoryListCreator返回的结果合并到一个List并传入ViewHolderGenerateHelper中。为了便于管理，建议为每个Adapter创建一个自己的ViewHolderGenerateHelper。如果后期有新加的category对应的ViewHolder要展示，那么修改这个类即可。

```java
public class FoodMaterialViewHolderGenerateHelper implements IViewHolderGenerateHelper<MarketInfo> {
    private CleanViewHolderGenerateHelper<MarketInfo> cleanViewHolderGenerateHelper;

    public FoodMaterialViewHolderGenerateHelper() {
        List<Object> foodMaterialList = new ArrayList<>();
        foodMaterialList.addAll(FoodMaterialViewHolderFactoryListCreator.createFactoryList());
        cleanViewHolderGenerateHelper = new CleanViewHolderGenerateHelper<>(foodMaterialList);
    }

    @Override
    public int getItemType(Object item) {
        return cleanViewHolderGenerateHelper.getItemType(item);
    }

    @Override
    public BaseCleanViewHolder createViewHolder(ViewGroup parent, int itemType, MarketInfo marketInfo) {
        return cleanViewHolderGenerateHelper.createViewHolder(parent, itemType, marketInfo);
    }
}

```

如果有一组ViewHolder需要展示在多个列表中，那么可以很方便的进行复用，只需要将其对应的ViewHolderFactoryListCreator生成的List添加到不同的Adapter中就可以实现了。


### 创建Adapter
我们提供了一个基类BaseCleanAdapter，如果没有什么特别的需求，那么你的Adapter实际上很简单，继承BaseCleanAdapter，指定范型即可，没有别的需要做的了。

```java
public class MarketAdapter extends BaseCleanAdapter<Object, MarketInfo> {

    public MarketAdapter(IViewHolderGenerateHelper<MarketInfo> viewHolderGenerateHelper, MarketInfo marketInfo) {
        super(viewHolderGenerateHelper, marketInfo);
    }

}
```

### 展示列表
好了，下面可以使用我们的Adapter来展示数据了。只需要给Adapter传入我们之前创建的对应的ViewHolderGenerateHelper即可。

```java
MarketInfo marketInfo = new MarketInfo("Baymax SuperMarket");
MarketAdapter marketAdapter = new MarketAdapter(new FoodMaterialViewHolderGenerateHelper(), marketInfo);

RecyclerView foodMaterialList = findViewById(R.id.food_material_list);
foodMaterialList.setAdapter(marketAdapter);
foodMaterialList.setLayoutManager(new LinearLayoutManager(this));
marketAdapter.updateData(createMarketData(), null);
```

### ExtraData
在前面的介绍中，可以看到有一个ExtraData的范型。

平时在我们的业务开发中，可能会遇到一些需要在ViewHolder中访问上级数据的情况，比如Avtivity、Fragment中的数据，仅仅通过onBindViewHolder传入的参数是不够的。那么可以定义一个数据结构，将ViewHolder中可能需要的数据封装进去，通过ViewHolder的构造函数传入。

## 总结
使用本项目，可以比较方便地从现有的代码进行切换，可以方便地对不同业务类型的列表展示元素进行管理，可以方便地在多个列表中复用ViewHolder，可以方便地增删ViewHolder，减少代码耦合。
