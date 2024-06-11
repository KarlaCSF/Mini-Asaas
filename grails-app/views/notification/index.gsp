<%@ page
        contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Notificações</title>
    </head>

    <body>
        <g:if test="${params.errorMessage}">
            <span>${params.errorMessage}</span>
        </g:if>

        <h2>Notificações</h2>
        <g:each var="notification" in="${notificationList}">
            <div style="border: solid 1px black; ${notification.isRead ? 'background-color: #f2f6fc; font-weight:300;' : 'background-color: #ffffff; font-weight:600;'} ">
                <div class="top-container" style="display: flex; justify-content: space-between">
                    <a href="${createLink(controller: 'notification', action: 'access', id: notification.id)}">${notification.title}</a><br>
                    <div style="display: flex; gap: 10px;">
                        <g:if test="${notification.isRead == false}">
                                <a href="${createLink(controller: 'notification', action: 'read', id: notification.id)}">Marcar como lida</a><br>
                        </g:if>
                        <g:else>
                            <a href="${createLink(controller: 'notification', action: 'unread', id: notification.id)}">
                                <a href="${createLink(controller: 'notification', action: 'unread', id: notification.id)}">Marcar como não lida</a><br>
                            </a>
                        </g:else>

                        <a href="${createLink(controller: 'notification', action: 'delete', id: notification.id)}">Apagar Notificação</a><br>
                    </div>
                </div>

                <p>${notification.description}</p>
            </div>
        </g:each>
    </body>
</html>