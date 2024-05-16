package xyz.echoes.echoeswhitelabel.di.activity_component

import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityKey(
  val value: KClass<out Any>,
)