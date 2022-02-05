package org.neocities.utils;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;

/**
 * https://neocities.org/site_files/allowed_types
 */
public enum AllowedFileTypes {
    asc, atom,
    bin,
    css, csv,
    dae,
    eot, epub,
    geojson, gif, gltf,
    htm, html,
    ico,
    jpeg, jpg, js, json,
    key, kml, knowl,
    less,
    manifest, markdown, md, mf, mid, midi, mtl,
    obj, opml, otf,
    pdf, pgp, png,
    rdf, rss,
    sass, scss, svg,
    text, tsv, ttf, txt,
    webapp, webmanifest, webp, woff, woff2,
    xcf, xml;

    public static final EnumSet<AllowedFileTypes> RECOMMENDED = EnumSet.of(
            htm, html,
            jpg, png, gif, svg, ico,
            md, markdown,
            js, json, geojson,
            css,
            txt, text, csv, tsv,
            xml,
            eot, ttf, woff, woff2
    );

    public static boolean hasAllowedExtension(final String fileName) {
        return fromExtension(fileName).isPresent();
    }

    public static boolean hasRecommendedExtension(final String fileName) {
        return fromExtension(fileName).map(RECOMMENDED::contains).orElse(false);
    }

    private static Optional<AllowedFileTypes> fromExtension(final String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(f.lastIndexOf(".") + 1))
                .map(AllowedFileTypes::fromString);
    }

    private static AllowedFileTypes fromString(final String name) {
        return Arrays.stream(values())
                .filter(v -> v.name().equals(name))
                .findAny()
                .orElse(null);
    }

}
