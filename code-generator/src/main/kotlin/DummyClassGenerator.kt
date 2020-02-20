import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class DummyClassGenerator : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment?): Boolean {

        val generatedSourcesRoot: String = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty()
        if (generatedSourcesRoot.isEmpty()) {
            processingEnv.messager.printMessage(
                Diagnostic.Kind.ERROR,
                "Can't find the target directory for generated Kotlin files."
            )
            return false
        }

        val typeBuilder = TypeSpec.classBuilder("GeneratedTest").addModifiers(KModifier.DATA)

        val constructorBuilder = FunSpec.constructorBuilder()

        for (i in 1..245) {
            val name = "a$i"
            constructorBuilder.addParameter(
                ParameterSpec.builder(name, Int::class)
                    .defaultValue("%L", i)
                    .build())

            typeBuilder.addProperty(
                PropertySpec.builder(name, Int::class)
                    .mutable()
                    .initializer(name)
                    .build()
            )

        }
        val file = FileSpec.builder("me.bristermitten.reflector", "GeneratedTest")
            .addType(
                typeBuilder
                    .primaryConstructor(constructorBuilder.build()).build()
            )
            .build()


        file.writeTo(File(generatedSourcesRoot))
        return false
    }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
}
