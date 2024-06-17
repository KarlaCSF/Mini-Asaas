<atlas-sidebar slot="sidebar" home-path="/">

    <atlas-sidebar-menu slot="body">

        <atlas-sidebar-menu-item
                icon="users"
                value="clients-group"
                text="Pagadores">

            <atlas-sidebar-menu-item
                    icon="users"
                    value="clients-group"
                    text="Cadastrar pagador"
                    href="${createLink(controller: "payer", action: "create")}">
            </atlas-sidebar-menu-item>

            <atlas-sidebar-menu-item
                    icon="users"
                    value="clients-group"
                    text="Lista de Pagadores"
                    href="${createLink(controller: "payer", action: "list")}">
            </atlas-sidebar-menu-item>
        </atlas-sidebar-menu-item>

        <atlas-sidebar-menu-item
                icon="money"
                value="payment-group"
                text="Cobranças">
            <atlas-sidebar-menu-item
                    icon="money"
                    value="payment-group"
                    text="Cadastrar cobrança"
                    href="${createLink(controller: "payment", action: "create")}">
            </atlas-sidebar-menu-item>

            <atlas-sidebar-menu-item
                    icon="money-notes"
                    value="payment-group"
                    text="Lista de cobranças"
                    href="${createLink(controller: "payment", action: "list")}">
            </atlas-sidebar-menu-item>
        </atlas-sidebar-menu-item>
    </atlas-sidebar-menu>
</atlas-sidebar>