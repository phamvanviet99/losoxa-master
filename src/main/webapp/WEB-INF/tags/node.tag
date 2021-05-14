<%@ attribute name="list" type="java.util.List<com.phamvanviet.losoxa.model.response.PermissionResponse>" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty list}">
    <c:forEach var="folderGroup" items="${list}">
        <li id="bs_1">
            <span class="plus">&nbsp;</span>
            <input name="permissionIds" type="checkbox" id="c_bs_1" value="${folderGroup.id}"/>
            <span>${folderGroup.name} - ${folderGroup.description} </span>
            <c:if test="${folderGroup.children.size()>0}">
                <ul id="bs_l_1" class="sub_ul" style="display: none">
                    <myTags:node list="${folderGroup.children}"/>
                </ul>
            </c:if>
        </li>
    </c:forEach>
</c:if>