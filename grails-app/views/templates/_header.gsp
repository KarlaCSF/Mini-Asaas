<%@ page import="com.mini.asaas.customer.Customer" %>
<% Customer currentUser = Customer.get(1) %> <!-- Fixed in 1 while dont have authentication method to get current user-->

<atlas-page-header slot="header">
    <g:render template="/templates/notification" slot="notification" model="[currentUserId: currentUser.id]"/>
    <g:render template="/templates/avatar" slot="avatar" model="[currentUserId: currentUser.id]"/>
</atlas-page-header>