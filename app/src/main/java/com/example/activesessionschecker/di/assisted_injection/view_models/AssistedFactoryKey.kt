package xyz.echoes.echoeswhitelabel.di.assisted_injection.view_models

import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class AssistedFactoryKey(
  val value: KClass<out Any>,
)