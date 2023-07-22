# LeoAdapter
[![Maven Central](https://img.shields.io/maven-central/v/com.adkhambek.leo/leo.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%com.adkhambek.leo)

```gradle
allprojects {
    repositories {
        ...
        mavenCentral()
    }
}
```

```grad
dependencies {
    implementation 'com.adkhambek.leo:leo:$lastVersion'
}
```

```kotlin
val leoAdapter: LeoAdapter<Person, ItemBinding> = binding.recycler.setupAdapter(
    ItemBinding::inflate
) { itemBinding, index, item ->
    itemBinding.textView.text = "${item.name}  $index"
    itemBinding.textView.setBackgroundResource(colors[index % colors.size])

    itemBinding.root.setOnClickListener {
        TODO("logic here")
    }
}

val data = (1..1000).map {
    Person(it, it, "Adam")
}

leoAdapter.setList(data)
```


```kotlin
val leoAdapter: LeoAdapter<Person, ItemBinding> = binding.viewPager2.setupAdapter(
    ItemBinding::inflate
) { itemBinding, index, item ->
    itemBinding.textView.text = "${item.name}  $index"
    itemBinding.textView.setBackgroundResource(colors[index % colors.size])

    itemBinding.root.setOnClickListener {
        TODO("logic here")
    }
}

val data = (1..1000).map {
    Person(it, it, "Adam")
}

leoAdapter.setList(data)
```

## Paging Adapter
```kotlin
val leoAdapter: LeoPagingAdapter<Person, ItemBinding> = binding.recycler.setupPagingAdapter(
    viewBinding = RecyclerItemBinding::inflate,
    diffCallback = DIFF_UTIL,
) { itemBinding, index, item -> // item is nullable 😢
    itemBinding.textView.text = "${item?.name}  $index"
    itemBinding.textView.setBackgroundResource(colors[index % colors.size])

    itemBinding.root.setOnClickListener {
        TODO("logic here")
    }
}

val data : PaginData<Person> = TODO()
leoAdapter.submitData(data)
```