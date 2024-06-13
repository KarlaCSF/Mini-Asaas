<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <title>${properties.emailHeadTitle}</title>
    </head>

    <body>
        <div align="center"
             style="width:100%;max-width:872px;margin:0 auto;background-color:#fff;border-radius:10px 10px 0 0">

            <div style="display:block;width:100%;border-top-right-radius:4px;border-top-left-radius:4px;overflow:hidden;letter-spacing:unset;box-sizing:border-box;font-family:'Open Sans',sans-serif!important">
                <div style="display:table;width:100%">
                    <div style="background-color:#0030b9;color:#ffffff;display:table-cell;padding:24px;word-break:break-word;text-align:left;width:100%">
                        <h1 style="color:#ffffff;margin:0;text-align:center;font-size:24px;font-style:normal;font-weight:600;line-height:28.8px;font-family:'Open Sans',sans-serif!important;letter-spacing:unset">
                            Bem-vindo ao Asaas
                        </h1>
                    </div>
                </div>
            </div>

            <div style="border-top:1px solid #cdcdcd;width:100%"></div>

        <div style="max-width:792px;padding:0 40px;color:#212529;font-size:16px;line-height:20px;margin:20px auto;text-align:left">
            <p style="font-weight:600;margin:0 0">
                Olá, ${properties.userUsername}.
            </p>

            <p style="margin:0 0 16px">
                Você foi adicionado como usuário na conta de ${properties.customerName}.
            </p>

            <p style="margin:0 0 16px">
                Sua senha de acesso: 

                <span style="font-weight:600">
                    ${properties.userPassword}
                </span>
            </p>

            <g:if test="${properties.editUserLink}">
                <p style="margin:0 0 16px;margin:0">
                    Caso deseje redefinir a senha, 
                    <a style="color:#0d6efd"
                       href=${properties.editUserLink}
                       target="_blank"
                       data-saferedirecturl= ${properties.editUserLink}>
                        <wbr>
                        clique aqui
                    </a>
                </p>
                </div>
            </g:if>

            <table style="width:100%;max-width:872px;margin:0;padding:16px 40px;text-align:center;background-color:#f8f9fa;line-height:20px;color:#212529">
                <tbody>
                <tr>
                    <td>

                        <p style="text-align:center;font-size:14px;margin:0 auto;font-weight:600">
                            ${properties.customerName}
                        </p>


                        <p style="text-align:center;font-size:14px;margin:0 auto">
                            <a style="text-decoration:none;font-size:14px;color:rgba(0,0,0,0.87)">
                                ${properties.customerEmail}
                            </a>
                        </p>

                        <p style="text-align:center;font-size:14px;margin:0 auto">
                            ${properties.customerState} - ${properties.customerCity}
                        </p>

                    </td>
                </tr>
                </tbody>
            </table>

        </div>
    </body>
</html>
