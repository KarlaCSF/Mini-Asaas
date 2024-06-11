package com.mini.asaas.atlas

class AtlasFormTagLib {

    static namespace = "atlasFormTagLib"

    def optionList = { attrs, body ->
        def list = attrs.from
        String messagePrefix = attrs.valueMessagePrefix
        String messageSuffix = attrs.messageSuffix
        String value = attrs.value
        String optionKey = attrs.optionKey
        String optionValue = attrs.optionValue
        String noSelectionLabel = attrs.noSelectionLabel

        out << g.render(template: "/atlas/forms/optionList", model: [
                list: list,
                messagePrefix: messagePrefix,
                messageSuffix: messageSuffix,
                value: value,
                optionKey: optionKey,
                optionValue: optionValue,
                noSelectionLabel: noSelectionLabel
        ])
    }
}
