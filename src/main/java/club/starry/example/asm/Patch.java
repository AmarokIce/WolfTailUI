package club.starry.example.asm;

import net.tclproject.mysteriumlib.PlaceholderCoremod;

public class Patch extends PlaceholderCoremod {
    public Patch() {}

    @Override
    public void registerFixes() {
        registerClassWithFixes(ExampleModASM.class.getName());
    }
}