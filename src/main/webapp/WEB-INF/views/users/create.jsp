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
    function validateAge() {
        var dateNaissance = new Date(document.getElementById("dateBirth").value);
        var ageDiffMs = Date.now() - dateNaissance.getTime();
        var ageDate = new Date(ageDiffMs);
        var age = Math.abs(ageDate.getUTCFullYear() - 1970);
        if (age < 18) {
            alert("Le client doit avoir au moins 18 ans !");
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
              Ajouter un utilisateur
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post" onsubmit="return validateAge()" >
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="last_name" class="col-sm-2 control-label">Nom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="last_name" name="last_name"
                                               required placeholder="Nom" minlength="3">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="first_name" class="col-sm-2 control-label">Prenom</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="first_name" name="first_name"
                                               required placeholder="Prenom" minlength="3">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>
                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email" required placeholder="Email">

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="dateBirth" class="col-sm-2 control-label">Date Of Birth</label>
                                    <div class="col-sm-10">
                                        <input type="date" class="form-control"
                                               id="dateBirth" name="dateBirth" placeholder="Date Of Birth" required>
                                    </div>
                                </div>

                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <a href="${pageContext.request.contextPath}/users">
                                    <button type="submit" class="btn btn-info pull-right">Ajouter</button>
                                </a>
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
</body>
</html>
