<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="person">
        <person>
            <xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
            <xsl:attribute name="surname"><xsl:value-of select="surname"/></xsl:attribute>
            <xsl:attribute name="patronymic"><xsl:value-of select="patronymic"/></xsl:attribute>
            <xsl:attribute name="birthDate">
                <xsl:choose>
                    <xsl:when test="string-length(birthDate) > 0">
                        <xsl:value-of select="concat(substring(birthDate, 9, 2), '.', substring(birthDate, 6, 2), '.', substring(birthDate, 1, 4))"/>
                    </xsl:when>
                    <xsl:otherwise></xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>
            <xsl:attribute name="gender"><xsl:value-of select="gender"/></xsl:attribute>

            <document>
                <xsl:attribute name="series"><xsl:value-of select="document/series"/></xsl:attribute>
                <xsl:attribute name="number"><xsl:value-of select="document/number"/></xsl:attribute>
                <xsl:attribute name="type"><xsl:value-of select="document/type"/></xsl:attribute>
                <xsl:attribute name="issueDate">
                    <xsl:choose>
                        <xsl:when test="string-length(document/issueDate) > 0">
                            <xsl:value-of select="concat(substring(document/issueDate, 9, 2), '.', substring(document/issueDate, 6, 2), '.', substring(document/issueDate, 1, 4))"/>
                        </xsl:when>
                        <xsl:otherwise></xsl:otherwise>
                    </xsl:choose>
                </xsl:attribute>
            </document>
        </person>
    </xsl:template>
</xsl:stylesheet>
