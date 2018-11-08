# CleanRecyclerViewAdapter
![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)
![JCenter](https://img.shields.io/badge/%20JCenter%20-0.1.0-5bc0de.svg)

It is easy for you to show many different items in recyclerview。You will not have to write many viewholders in recyclerview adapter, and declare many viewtypes, and try to create and bind them. You only need to write a viewholder and its factory with annotation. Then, everything is done.

## [中文](https://github.com/liwshuo/CleanRecyclerViewAdapter/blob/master/README.md)|[English](https://github.com/liwshuo/CleanRecyclerViewAdapter/blob/master/README_EN.md)

## Getting Started
Add these lines in your build.gradle file in your module.
```groovy
implementation 'com.baymax.clean_adapter:clean_adapter:0.1.0'
implementation 'com.baymax.clean_adapter:clean_adapter_annotation:0.1.0'
annotationProcessor 'com.baymax.clean_adapter:clean_adapter_compiler:0.1.0'
```

## Usage
### Create ViewHolder
All of your ViewHolders should extend BaseCleanViewHolder or BaseCleanExtraDataViewHolder.
* If you need pass ExtraData to your ViewHolder, you should extend BaseCleanExtraDataViewHolder.
* if you do not need pass ExtraData, you can extend BaseCleanViewHolder.

```java
public class AppleViewHolder extends BaseCleanExtraDataViewHolder<Fruit, MarketInfo> {
    private TextView fruitName;

    public AppleViewHolder(ViewGroup parent) {
        super(parent, R.layout.layout_apple_viewholder);
        fruitName = findViewById(R.id.name);
    }

    @Override
    public void onBindViewHolder(Fruit fruit, MarketInfo marketInfo) {
        fruitName.setText(fruit.name);
    }
}


public class PorkViewHolder extends BaseCleanViewHolder<Meat> {
    private TextView meatName;

    public PorkViewHolder(ViewGroup parent) {
        super(parent, R.layout.layout_pork_viewholder);
        meatName = findViewById(R.id.name);
    }

    @Override
    public void onBindViewHolder(Meat meat) {
        meatName.setText(meat.name);
    }
}
```

### Create ViewHolderFactory
Create a ViewHolderFactory for your ViewHolder, you can extend AbstractViewHolderFactory or implement IViewHolderFactory.

ViewHolderFactory is used to bind your data bean to its ViewHolders, 1 to N.

If a data bean is bind to many ViewHolders, there will be many initialize code for these ViewHolders. To avoid this situation, you can extend AbstractViewHolderFactory. AbstractViewHolderFactory implements IViewHolderFactory but implements is create method. In create methods, it creates ViewHolder with reflection.

But if you care about reflect efficient, you should initialize ViewHolders by yourself.

```java
public abstract class AbstractViewHolderFactory<Item> implements IViewHolderFactory<Item> {

    @Override
    public BaseCleanExtraDataViewHolder create(ViewGroup parent, Class viewHolderClass) {
        BaseCleanExtraDataViewHolder viewHolder = null;
        Exception exception = null;
        try {
            Constructor constructor = viewHolderClass.getConstructor(ViewGroup.class);
            viewHolder = (BaseCleanExtraDataViewHolder) constructor.newInstance(parent);
        } catch (NoSuchMethodException e) {
            exception = e;
        } catch (IllegalAccessException e) {
            exception = e;
        } catch (InstantiationException e) {
            exception = e;
        } catch (InvocationTargetException e) {
            exception = e;
        }
        if (exception != null && BuildConfig.DEBUG) {
            IllegalArgumentException argumentException;
            if (exception instanceof InvocationTargetException) {
                argumentException = new IllegalArgumentException(((InvocationTargetException) exception).getTargetException());
                argumentException.setStackTrace(((InvocationTargetException) exception).getTargetException().getStackTrace());
            } else {
                argumentException = new IllegalArgumentException(exception);
                argumentException.setStackTrace(exception.getStackTrace());
            }
            throw argumentException;
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

Here is the example for creating ViewHolderFactory.

If a data bean is bind to one ViewHolder, it is easy like this.
```java
@ViewHolderFactory(category = "FoodMaterial")
public class VegetableViewHolderFactory extends AbstractViewHolderFactory<Vegetable> {
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

But if a data bean is bind to multi ViewHolders
```java
@ViewHolderFactory(category = "FoodMaterial")
public class FruitViewHolderFactory extends AbstractViewHolderFactory<Fruit> {
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
                return DummyCleanViewHolder.class;
        }
    }

}
```

Please do not forget to add annotation @ViewHolderFactory. This annotation has an argument named category. It is used to create  a class named $categoryViewHolderFactoryListCreator when compling. All the view holders with the same category will be put into the same class.
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

### Create ViewHolderGenerateHelper
ViewHolderGenerateHelper is used to store all the mapping data which will  be used to create and bind view holder in adapter. These mapping data are  calculated with all the ViewHolderFactory info passed into the CleanViewHolderGenerateHelper.
```java
public class FoodMaterialViewHolderGenerateHelper implements IViewHolderGenerateHelper {
    private CleanViewHolderGenerateHelper cleanViewHolderGenerateHelper;

    public FoodMaterialViewHolderGenerateHelper() {
        List<Object> foodMaterialList = new ArrayList<>();
        foodMaterialList.addAll(FoodMaterialViewHolderFactoryListCreator.createFactoryList());
        cleanViewHolderGenerateHelper = new CleanViewHolderGenerateHelper(foodMaterialList);
    }

    @Override
    public int getItemType(Object item) {
        return cleanViewHolderGenerateHelper.getItemType(item);
    }

    @Override
    public BaseCleanExtraDataViewHolder createViewHolder(ViewGroup parent, int itemType) {
        return cleanViewHolderGenerateHelper.createViewHolder(parent, itemType);
    }
}
```

### Create Adapter
Create adapter extends BaseCleanAdapter.
```java
public class MarketAdapter extends BaseCleanAdapter<Object, MarketInfo> {

    public MarketAdapter(IViewHolderGenerateHelper<MarketInfo> viewHolderGenerateHelper, MarketInfo marketInfo) {
        super(viewHolderGenerateHelper, marketInfo);
    }

}
```

### Show your List
Everything is done.
```java
MarketInfo marketInfo = new MarketInfo("Baymax SuperMarket");
MarketAdapter marketAdapter = new MarketAdapter(new FoodMaterialViewHolderGenerateHelper(), marketInfo);

RecyclerView foodMaterialList = findViewById(R.id.food_material_list);
foodMaterialList.setAdapter(marketAdapter);
foodMaterialList.setLayoutManager(new LinearLayoutManager(this));
marketAdapter.updateData(createMarketData(), null);
```

#Android
