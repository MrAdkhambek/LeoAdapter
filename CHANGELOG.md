# Change Log

## v0.0.4 *(2023-07-22)*

- Breaking changes Adapter LeoAdapter structure
```kotlin
// Old structure
val leoAdapter: LeoAdapter<Person> = binding.recycler.setupAdapter(
    getViewBinding = RecyclerItemBinding::inflate,
    diffUtil = DIFF_UTIL,
) { itemBinding, index, item ->
    itemBinding.textView.text = "${item.name}  $index"
    itemBinding.textView.setBackgroundResource(colors[index % colors.size])

    itemBinding.root.setOnClickListener {
        TODO("logic here")
    }
}

// New structure
val leoAdapter: LeoAdapter<Person, ItemBinding> = binding.recycler.setupAdapter(
    viewBinding = RecyclerItemBinding::inflate,
    diffCallback = DIFF_UTIL,
) { itemBinding, index, item ->
    itemBinding.textView.text = "${item.name}  $index"
    itemBinding.textView.setBackgroundResource(colors[index % colors.size])

    itemBinding.root.setOnClickListener {
        TODO("logic here")
    }
}
```

- New paging support

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
```

## v0.0.3 *(2023-03-02)*

- Update docs
- fix CI issue

## v0.0.2 *(2023-03-01)*

- Update docs

## v0.0.1 *(2023-03-01)*

- First release version