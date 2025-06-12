Wolf Tail UI
<img align="right" alt="Logo" width="128" height="144" src="https://github.com/AmarokIce/WolfTailUI/blob/main/image/Icon.png">
=======
### The game UI library for Minecraft 1.7.10.

#### [MCMOD]() - [CurseForge](https://www.curseforge.com/minecraft/mc-mods/wolftailui/) - [Modrinth](https://modrinth.com/mod/wolftailui/)


## Quick Start
**Import**:  
TODO: Please use CurseMaven or Modrinth Maven.

**Simple GuiScreen:**  
```kotlin
Minecraft.getMinecraft().displayGuiScreen(object: WGuiBased(title= "wolftail_testUI", size = Pair(200, 180), lightStyle = true) {
    override fun start() {
        this.addWidget(WString("The string of 'Hello' from WolfTailUI!", Pair(5, 5)))
        this.addWidget(WButton("And a button!", Pair(5, 20), Pair(95, 40), false) {})
        this.addWidget(WString("The string of 'Hello' from WolfTailUI!", Pair(13, 180 - 15), color = Color(64, 76, 94), shadow = Color.WHITE))
        this.addWidget(WButton("And a button!", Pair(200 - 95, 180 - 40), Pair(200 - 5, 180 - 20), true) {})
    }
})
```

**Poas a toast:**  
```kotlin
WolfTailUI.addToast(IToast.create("Hello", "World", StyleToast.TOAST_DARK_UI, Items.pumpkin_pie))
```

## Find out

This project use color in default is [Nord](https://github.com/nordtheme/nord). thanks NordTheme create this color palette.

<img align="left" alt="UI" width="400" height="360" src="https://github.com/AmarokIce/WolfTailUI/blob/main/image/TheUI.png">
<img align="right" alt="Logo" width="256" height="128" src="https://github.com/AmarokIce/WolfTailUI/blob/main/image/Toasts.png">
## Sponsorship

[![](https://github.com/AmarokIce/AmarokIce/blob/main/img/AiFaDian.png)](https://ifdian.net/a/AmarokIce)
[![](https://github.com/AmarokIce/AmarokIce/blob/main/img/BuyMeACoffee.png)](https://buymeacoffee.com/AmarokIce)
[![](https://github.com/AmarokIce/AmarokIce/blob/main/img/Blog.png)](https://wolf.snowlyicewolf.club)