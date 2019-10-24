package bergco.se.mvvm.extensions

import org.koin.core.KoinContext
import org.koin.core.parameter.ParameterDefinition
import org.koin.core.parameter.emptyParameterDefinition
import org.koin.core.scope.Scope
import org.koin.standalone.StandAloneContext

/**
 * Helper class to inject koin modules into anything
 * */
inline fun <reified T : Any> Any.inject(
    name: String = "",
    scope: Scope? = null,
    noinline parameters: ParameterDefinition = emptyParameterDefinition()
) =
    lazy { get<T>(name, scope, parameters) }

inline fun <reified T : Any> Any.get(
    name: String = "",
    scope: Scope? = null,
    noinline parameters: ParameterDefinition = emptyParameterDefinition()
): T =
    getKoin().get(name, scope, parameters)

fun Any.getKoin(): KoinContext = StandAloneContext.getKoin().koinContext
