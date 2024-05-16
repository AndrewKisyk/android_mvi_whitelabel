package xyz.echoes.echoeswhitelabel.di.activity_component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

typealias ActivityComponentDependencies = @JvmSuppressWildcards Map<Class<out Any>, Any>

@Composable
inline fun <reified D : Any> activityDependency(): D {
    val dependencies = LocalActivityComponentDependencies.current
    return dependencies[D::class.java] as D
}

@Composable
fun ProvideActivityComponentDependencies(
    dependencies: ActivityComponentDependencies,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalActivityComponentDependencies provides dependencies,
        content = content
    )
}
val LocalActivityComponentDependencies = staticCompositionLocalOf<ActivityComponentDependencies> { emptyMap() }
