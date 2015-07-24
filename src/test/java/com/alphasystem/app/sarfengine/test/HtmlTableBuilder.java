package com.alphasystem.app.sarfengine.test;

import static java.lang.String.format;

/**
 * @author sali
 */
public final class HtmlTableBuilder implements Constants {

    private final int numOfColumns;
    private final Double[] columnWidthPercentages;
    StringBuilder builder = new StringBuilder();

    public HtmlTableBuilder(Double... columnWidthPercentages) {
        this.numOfColumns = columnWidthPercentages == null ? 0 : columnWidthPercentages.length;
        this.columnWidthPercentages = columnWidthPercentages;
    }

    public HtmlTableBuilder startTable() {
        builder.append(TABLE_DECLERATION_START);
        if (numOfColumns > 0) {
            for (Double columnWidthPercentage : columnWidthPercentages) {
                builder.append(format("<col style=\"width: %s%%\"/>", columnWidthPercentage));
            }
        }
        return this;
    }

    public HtmlTableBuilder endTable() {
        builder.append(TABLE_BODY_DECLERATION_END);
        return this;
    }

    public HtmlTableBuilder startTableHeader() {
        builder.append(TABLE_HEADER_DECLERATION_START);
        return this;
    }

    public HtmlTableBuilder endTableHeader() {
        builder.append(TABLE_BODY_DECLERATION_END);
        return this;
    }

    public HtmlTableBuilder startTableRow() {
        builder.append(START_TABLE_ROW);
        return this;
    }

    public HtmlTableBuilder endTableRow() {
        builder.append(END_TABLE_ROW);
        return this;
    }

    public HtmlTableBuilder addHeader(int colspan, String value) {
        String colspanStr = colspan <= 0 ? "" : format(" colspan=\"%s\"", colspan);
        builder.append(format("<th%s></th>", colspanStr));
        return this;
    }

    public void clear() {
        builder.setLength(0);
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
