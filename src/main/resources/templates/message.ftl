<#import "parts/common.ftl" as c>
<#import "parts/logout.ftl" as out>

<@c.page>
    <div>
        <@out.logout />
        <span><a href="/user/">User List</a></span>
    </div>
    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Message" />
            <input type="text" name="tag" placeholder="Tag" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Додати</button>
        </form>
    </div>
    <div>Message list</div>
    <form method="get" action="/message">
        <input type="text" name="filter">
        <button type="submit">Search</button>
    </form>

    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <id>${message.tag} |</id>
            <strong>${message.authorName}</strong>
        </div>
    <#else>
        No message
    </#list>
</@c.page>