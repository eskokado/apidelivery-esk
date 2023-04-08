package com.anjun.eskokado;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = {
                @Tag(name="manager", description="Manager operations."),
        },
        info = @Info(
                title="Manager Delivery Esk API",
                description = "A API criada possui os seguintes requisitos:\n" +
                        "\n" +
                        "Criar a entrega de um item.\n" +
                        "Procurar pela entrega de um item específico.\n" +
                        "Procurar entregas por destinatário ou por remetente.\n" +
                        "Atualizar o estado como entregue.\n" +
                        "Cancelar uma entrega.\n" +
                        "Consumir uma outra API REST já existente para buscar dados, como bairro, rua e geolocalização, a partir de CEP e número informados.\n" +
                        "Fluxo de Autenticação usando bearer token.\n" +
                        "Documentação usando o Swagger.\n" +
                        "Essa API permite gerenciar entregas de itens, permitindo a criação, consulta e atualização do status de entrega, bem como a busca por entregas por destinatário ou remetente. Além disso, ela consome uma API externa para buscar informações adicionais sobre os endereços de entrega.\n" +
                        "\n" +
                        "A segurança da API é garantida por um fluxo de autenticação, que requer o uso de um token de autenticação válido para acessar os recursos protegidos.\n" +
                        "\n" +
                        "A documentação da API é fornecida pelo Swagger, facilitando o uso e a compreensão dos recursos disponíveis na API.\n" +
                        "\n" +
                        "A api tem dois usuários POJO como username: user ou admin e senha: user ou admin",
                version = "1.0",
                contact = @Contact(
                        name = "Manager Delivery Esk API Support",
                        url = "https://localhost:8080",
                        email = "eskokado@gmail.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"))

)
public class ApiDeliveryEskApplication extends Application {
}
