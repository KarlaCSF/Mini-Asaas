<%@ page import="com.mini.asaas.repositories.NotificationRepository" %>
<% int unreadNotificationCount = NotificationRepository.countUnreadByCustomerId(currentUserId); %>

<div slot="actions">
        <atlas-button
                icon="bell"
                href="${createLink(controller: "notification", action: "index")}"
                badge-number="${unreadNotificationCount}"
                theme= ${unreadNotificationCount ? "warning" : "primary"}
                ${unreadNotificationCount ? "show-badge" : ""}
                hoverable>
        </atlas-button>
</div>
