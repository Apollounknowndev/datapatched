package dev.worldgen.datapatched.impl.loot.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.impl.Datapatched;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;

public record CommonData(Predicate<Identifier> target, int priority) {
    private static final Identifier UNKNOWN_ID = Datapatched.id("unknown");
    private static final Codec<Predicate<Identifier>> TARGET_CODEC = Codec.withAlternative(
        ExtraCodecs.PATTERN.fieldOf("regex").codec().xmap(pattern -> table -> pattern.asPredicate().test(table.toString()), predicate -> Pattern.compile("")),
        Codec.withAlternative(
            Identifier.CODEC.xmap(key -> key::equals, predicate -> UNKNOWN_ID),
            Identifier.CODEC.listOf().xmap(list -> list::contains, predicate -> List.of(UNKNOWN_ID))
        )
    );

    public static MapCodec<CommonData> codec(int defaultPriority) {
        return RecordCodecBuilder.mapCodec(instance -> instance.group(
            TARGET_CODEC.fieldOf("targets").forGetter(CommonData::target),
            Codec.INT.fieldOf("priority").orElse(defaultPriority).forGetter(CommonData::priority)
        ).apply(instance, CommonData::new));
    }
}

