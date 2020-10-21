# LeoAdapter
[![](https://jitpack.io/v/MrAdkhambek/LeoAdapter.svg)](https://jitpack.io/#MrAdkhambek/LeoAdapter)


```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

```grad
dependencies {
    implementation 'com.github.MrAdkhambek:LeoAdapter:$lastVersion'
}
```

```kotlin
    val leoAdapter: LeoAdapter<Person> = recycler.setupAdapter(
        R.layout.recycler_item,
        DIFF_UTIL
    ) {
        val textView: TextView = findViewById(R.id.textView)

        bind { _, index, item ->
            textView.text = item.name
            textView.setBackgroundResource(colors[index % colors.size])
        }
    }

    leoAdapter.setList(data)
```


```kotlin
    val leoAdapter: LeoAdapter<Person> = viewPager2.setupAdapter(
        R.layout.recycler_item,
        DIFF_UTIL
    ) {
        val textView: TextView = findViewById(R.id.textView)

        bind { _, index, item ->
            textView.text = item.name
            textView.setBackgroundResource(colors[index % colors.size])
        }
    }

    leoAdapter.setList(data)
```


```kotlin
    val leoAdapter: LeoAdapter<Person> = spinner.setupAdapter(
        R.layout.recycler_item
    ) {
        val textView: TextView = findViewById(R.id.textView)

        bind { _, index, item ->
            textView.text = item.name
            textView.setBackgroundResource(colors[index % colors.size])
        }
    }

    leoAdapter.setList(data)
```

![Image](media/carbon1.png)
