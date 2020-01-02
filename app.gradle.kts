apply {
    plugin("com.android.application")
    from("$rootDir/base.gradle")
    from("$rootDir/module_reference_data.gradle")
    from("$rootDir/module_reference_core.gradle")
    from("$rootDir/module_reference_feature.gradle")
}
