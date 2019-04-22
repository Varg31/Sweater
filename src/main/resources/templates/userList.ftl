<#import "parts/common.ftl" as c>

<@c.page>
    <div>List of users</div>
    <div>${message?ifExists}</div>

    <table>
        <thread>
            <th>Name</th>
            <th>Role</th>
            <th>Active status</th>
            <th></th>
            <th></th>
        </thread>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td>${user.activationCode?exists?then("Non", "Activated")}</td>
                <td><a href="/user/${user.id}">Edit</a> </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>

