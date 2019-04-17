<#import "parts/common.ftl" as c>
<#import "parts/logout.ftl" as out>

<@c.page>
    <div>
        <@out.logout />
        <span><a href="/user/">User List</a></span>
    </div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Message" />
            <input type="text" name="tag" placeholder="Tag" />
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Додати</button>
        </form>
    </div>
    <div>Message list</div>
    <form method="get" action="/message">
        <input type="text" name="filter" value="${filter?if_exists}">
        <button type="submit">Search</button>
    </form>

    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag} |</i>
            <strong>${message.authorName}</strong>
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}">
                </#if>
            </div>
        </div>
    <#else>
        No message
    </#list>
</@c.page>