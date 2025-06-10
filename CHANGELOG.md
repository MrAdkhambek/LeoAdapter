# Change Log

## v0.0.7 _(2025-06-10)_

- Breaking changes Adapter LeoAdapter structure

```kotlin
    leoAdapter.setList(data) // method is removed
    leoAdapter.submitData(data) // use this method instead of setList
```

- New create methods without `LayoutManager` and `recyclerView.setAdapter` // usefull for `MergeAdapter`

```kotlin
public fun <T : Any, VB : ViewBinding> RecyclerView.createAdapter(
    viewBinding: LeoItemBinding<VB>,
    inflater: LayoutInflater = LayoutInflater.from(context),
    onBindPayloads: LeoItemBindPayloadListener<T, VB> = DefaultLeoItemBindPayloadListener(),
    onBind: LeoItemBindListener<T, VB>,
)
```

## v0.0.4 _(2023-07-22)_

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

## v0.0.3 _(2023-03-02)_

- Update docs
- fix CI issue

## v0.0.2 _(2023-03-01)_

- Update docs

## v0.0.1 _(2023-03-01)_

- First release version
