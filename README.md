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
        textView.setOnClickListener {
            val item = getItem(currentPosition)
            Toast.makeText(it.context, "Name : ${item.name}  Age : ${item.age}", Toast.LENGTH_SHORT).show()
        }

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
        textView.setOnClickListener {
            val item = getItem(currentPosition)
            Toast.makeText(it.context, "Name : ${item.name}  Age : ${item.age}", Toast.LENGTH_SHORT).show()
        }

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
        textView.setOnClickListener {
            val item = getItem(currentPosition)
            Toast.makeText(it.context, "Name : ${item.name}  Age : ${item.age}", Toast.LENGTH_SHORT).show()
        }

        bind { _, index, item ->
            textView.text = item.name
            textView.setBackgroundResource(colors[index % colors.size])
        }
    }

    leoAdapter.setList(data)
```
```kotlin
data class Person(
    val id: Int,
    val age: Int,
    val name: String
)
```

![Image](media/carbon1.png)
