<g:if test="${noSelectionLabel}">
    <atlas-option
            value=""
            label="${noSelectionLabel}"
        ${ value == "" ? "selected" : ""}
    ></atlas-option>
</g:if>

<g:if test="${messagePrefix || messageSuffix}">
    <g:each var="option" in="${list}">
        <%
            String messageCode = ""

            if (messagePrefix) {
                messageCode = messagePrefix + "."
            }

            messageCode += option.toString()

            if (messageSuffix) {
                messageCode += "." + messageSuffix
            }
        %>
        <atlas-option
                value="${option.toString()}"
                label="${g.message(code: messageCode)}"
            ${ value == option.toString() ? "selected" : ""}
        ></atlas-option>
    </g:each>
</g:if>
<g:else>
    <g:if test="${optionKey && optionValue}">
        <g:each var="option" in="${list}">
            <atlas-option
                    value="${option[optionKey]}"
                    label="${option[optionValue]}"
                ${ value == option[optionKey].toString() ? "selected" : ""}
            ></atlas-option>
        </g:each>
    </g:if>
    <g:else>
        <g:each var="option" in="${list}">
            <atlas-option
                    value="${option}"
                    label="${option}"
                ${ value == option.toString() ? "selected" : ""}
            ></atlas-option>
        </g:each>
    </g:else>
</g:else>
