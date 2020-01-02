apply {
    plugin("com.android.library")
    from("$rootDir/base.gradle")
    from("$rootDir/module_reference_core.gradle")
    from("$rootDir/module_reference_data.gradle")
}
