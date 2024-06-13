<%@ page import="com.mini.asaas.repositories.NotificationRepository" %>
<% int noReadNotification = NotificationRepository.countUnreadByCustomerId(currentUserId); %>

<div slot="actions">
        <atlas-button
                icon="bell"
                href="${createLink(controller: "notification", action: "index")}"
                badge-number="${noReadNotification}"
                theme= ${noReadNotification ? "warning" : "primary"}
                ${noReadNotification ? "show-badge" : ""}
                hoverable>
        </atlas-button>
</div>
