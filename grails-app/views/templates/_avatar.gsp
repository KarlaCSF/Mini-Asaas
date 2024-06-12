<div slot="actions">
    <atlas-avatar
            data-atlas-dropdown="profile-dropdown"
            hoverable
            show-carret>
    </atlas-avatar>

    <atlas-dropdown
            id="profile-dropdown"
            placement="bottom-start"
            trigger="click"
            width="300"
            auto-close
            auto-close-trigger="outside">

        <atlas-dropdown-item
                icon="cog"
                theme="secondary">
                Meu perfil
        </atlas-dropdown-item>

        <atlas-dropdown-item
                href="${createLink(controller: "user", action: "logout")}"
                icon="power"
                theme="danger">
                Sair
        </atlas-dropdown-item>
    </atlas-dropdown>
</div>
