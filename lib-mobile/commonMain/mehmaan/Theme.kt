package app.mehmaan.mobile.mehmaan

data class Theme(
    val primaryColor: String,
    val secondaryColor: String,
    val backgroundColor: String,
    val surfaceColor: String,
    val errorColor: String,
    val onPrimaryColor: String,
    val onSecondaryColor: String,
    val onBackgroundColor: String,
    val onSurfaceColor: String,
    val onErrorColor: String,
    val typography: Typography,
    val shapes: Shapes,
    val elevation: Elevation
)

data class Typography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
    val overline: TextStyle
)

data class TextStyle(
    val fontFamily: String,
    val fontWeight: String,
    val fontSize: Float,
    val letterSpacing: Float,
    val lineHeight: Float,
    val color: String
)

data class Shapes(
    val small: Shape,
    val medium: Shape,
    val large: Shape
)

data class Shape(
    val cornerFamily: String,
    val cornerSize: Float
)

data class Elevation(
    val default: Float,
    val medium: Float,
    val high: Float
)