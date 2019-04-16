<#import "parts/common.ftl" as c>
<#import "parts/logout.ftl" as out>

<@c.page>
    <@out.logout />
    <form method="post">
        <input type="text" name="model" placeholder="Model" />
        <input type="number" name="ram" placeholder="RAM volume">
        <input type="double" name="speed" placeholder="Processor speed">
        <input type="number" name="hd" placeholder="HD volume">
        <input type="number" name="screen" placeholder="Screen diagonal">
        <input type="number" name="price" placeholder="Price">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Додати</button>
    </form>

    <#list laptops as laptop>
        <div>
            <b>${laptop.id}</b>
            <span>${laptop.model} |</span>
            <i>${laptop.ram}  |</i>
            <i>${laptop.speed}  |</i>
            <i>${laptop.price}    |</i>
            <i>${laptop.hd}   |</i>
            <i>${laptop.screen}</i>
        </div>
        <#else>
        No product
    </#list>
</@c.page>