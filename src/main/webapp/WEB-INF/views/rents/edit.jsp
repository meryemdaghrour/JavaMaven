<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
    <script>

        function validateForm() {
            var startDate = document.getElementById("begin").value;
            var endDate = document.getElementById("end").value;
            var currentDate = new Date().toISOString().split('T')[0];

            if (startDate < currentDate) {
                alert("La date de début de la réservation doit être supérieure ou égale à la date actuelle.");
                return false;
            }

            if (startDate >= endDate) {
                alert("La date de debut doit etre anterieure a la date de fin de la reservation.");
                return false;
            }
            return true;
        }

    </script>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Reservations
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post"  onsubmit="return validateForm()"
                              id="form-reservation" >
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="client" class="col-sm-2 control-label">Client</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="client" name="client" >
                                            <option value="${c.identifier}" selected> ${c.name} ${c.lastName}</option>
                                            <c:forEach items="${clients}" var ="client">
                                                <option value="${client.identifier}">${client.name} ${client.lastName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="rent" class="col-sm-2 control-label">Voiture</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="rent" name="rent">
                                            <option value="${v.identifier}" selected>${v.constructor} ${v.model}</option>
                                            <c:forEach items="${rents}" var ="rent">
                                                <option value="${rent.identifier}">${rent.constructor} ${rent.model}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="begin" class="col-sm-2 control-label">Date de debut</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control"
                                               id="begin" name="begin" placeholder="Date Debut" required
                                        value="${reservation.debut}"
                                        >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="end" class="col-sm-2 control-label">Date de fin</label>

                                    <div class="col-sm-10">

                                        <input type="date" class="form-control"
                                               id="end" name="end" placeholder="Date Fin" required
                                               value="${reservation.fin}"
                                        >
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">Modifier</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script>
    $(function () {
        $('[data-mask]').inputmask()
    });
</script>
</body>
</html>
