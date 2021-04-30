package tech.quilldev.Roles;

import net.kyori.adventure.text.format.TextColor;

public enum Rank {
    MEMBER(0, "Member", TextColor.color(59, 255, 70)),
    BUILDER(95, "Builder", TextColor.color(140, 3, 8)),
    ADMIN(98, "Admin", TextColor.color(255, 76, 57)),
    DEVELOPER(99, "Dev", TextColor.color(49, 255, 252)),
    OWNER(100, "Owner", TextColor.color(221, 33, 255));

    public final int value;
    public final String label;
    public final TextColor color;

    Rank(int value, String label, TextColor color) {
        this.value = value;
        this.label = label;
        this.color = color;
    }

    public static Rank getFromValue(int value) {
        for (final var key : Rank.values()) {
            if (value == key.value) {
                return key;
            }
        }
        return null;
    }

}

