package club.someoneice.wolftail.api

interface IWidgetWithStyle: IWidget {
    /**
     * The based resource path for UI.
     */
    fun getStyle(): IUIStyle
}
