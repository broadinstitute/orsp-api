<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <#--<link rel="icon" href="../../favicon.ico">-->

    <title>ORSP API</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="//oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 60px;
        }
    </style>

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">ORSP APIs</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>

<div class="container">
    <h1>ORSP APIs</h1>

    <div role="tabpanel">

        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="tab active"><a href="#irb" aria-controls="irb" role="tab" data-toggle="tab">IRB APIs</a></li>
            <li role="presentation" class="tab"><a href="#profile" aria-controls="sample" role="tab" data-toggle="tab">Sample Collection APIs</a></li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="irb">
<pre>
    class Irb {
        @NotNull
        String id

        @NotNull
        String summary

        @NotNull
        String location

        @NotNull
        List&lt;String> managers

        @Nullable
        String protocol

        @Nullable
        String status

        @Nullable
        List&lt;String> sampleCollections
    }

    GET     /irb (org.broadinstitute.orsp.api.resources.IrbResource)
    GET     /irb/{id} (org.broadinstitute.orsp.api.resources.IrbResource)
    GET     /irb/find/{term} (org.broadinstitute.orsp.api.resources.IrbResource)
    POST    /irb (org.broadinstitute.orsp.api.resources.IrbResource)
    DELETE  /irb (org.broadinstitute.orsp.api.resources.IrbResource)
    DELETE  /irb/{id} (org.broadinstitute.orsp.api.resources.IrbResource)
</pre>

            </div>
            <div role="tabpanel" class="tab-pane" id="sample">
<pre>
    class SampleCollection {

        @NotNull
        String id

        @NotNull
        String name

        @NotNull
        String category

        @NotNull
        String groupName

        @NotNull
        String archived

    }

    GET     /collection (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    GET     /collection/{id} (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    GET     /collection/find/{term} (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    POST    /collection (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    POST    /collection/findAll (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    DELETE  /collection (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    DELETE  /collection/{id} (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
</pre>
            </div>
        </div>

    </div>

</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        $('.tab a').on('click', function (e) {
            e.preventDefault();
            $(this).tab('show');
        });
    });
</script>
</body>
</html>