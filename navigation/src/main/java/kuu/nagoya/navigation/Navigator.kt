package kuu.nagoya.navigation

interface AppNavigation {
    fun navigateToDashboard()
    fun navigateToWordChoose()
}

interface DashboardNavigation {
    fun navigateAnalyzer()
}

interface RecordNavigation {
    fun navigateToAnalyzer()
}