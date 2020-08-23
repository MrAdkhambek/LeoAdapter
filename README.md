# LeoAdapter
[![](https://jitpack.io/v/MrAdkhambek/LeoAdapter.svg)](https://jitpack.io/#MrAdkhambek/LeoAdapter)


```groovy
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
   	}

	dependencies {
	        implementation 'com.github.MrAdkhambek:LeoAdapter:0.2.2'
	}
```

```kotlin
    val leoAdapter = recycler.setupAdapter(
            R.layout.recycler_item
        ) {
            bind { view, index, item ->
                view.findViewById<TextView>(R.id.textView).apply {
                    text = item.name
                    setBackgroundResource(color[index % color.size])
            }
        }
    }
```

```kotlin
    val leoAdapter = recycler.setupAdapter(
            R.layout.recycler_item,
            LinearLayoutManager(recycler.context)
        ) {
            bind { view, index, item ->
                view.findViewById<TextView>(R.id.textView).apply {
                    text = item.name
                    setBackgroundResource(color[index % color.size])
            }
        }
    }
```


```kotlin
    val leoAdapter = recycler.setupAdapter(
            R.layout.recycler_item,
            DIFF_ITEM_CALLBACK,
            LinearLayoutManager(recycler.context)
        ) {
            bind { view, index, item ->
                view.findViewById<TextView>(R.id.textView).apply {
                    text = item.name
                    setBackgroundResource(color[index % color.size])
            }
        }
    }
```

![Image](media/carbon1.png)
