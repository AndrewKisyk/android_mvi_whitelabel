package xyz.echoes.echoeswhitelabel.di.assisted_injection.view_models

import javax.inject.Qualifier

@Qualifier
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class AssistedFactoryQualifier