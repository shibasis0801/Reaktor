@file:JsExport
package app.mehmaan.core.design

import react.FC
import react.Props
import react.create
import react.dom.html.ReactHTML.button
import react.useState
import react.dom.*
import web.prompts.prompt

external interface ButtonProps: Props {
    var text: String
}

val ReaktorButton = FC<ButtonProps> { props ->
    val (count, setCount) = useState(0)
    button {
        onClick = {
            prompt("Hello from kotlin $count")
            setCount(count + 1)
        }
        + props.text
    }
}
