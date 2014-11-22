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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        body {
            padding-top:60px;
            padding-bottom: 60px;
        }
    </style>

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
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
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">
    <h1>ORSP APIs</h1>

    <h2>IRB APIs</h2>
    <pre>
    class Irb {
        @NotNull
        String id

        @NotNull
        String summary

        @NotNull
        String location

        @NotNull
        List<String> managers

        @Nullable
        String protocol

        @Nullable
        String status

        @Nullable
        List<String> sampleCollections
    }


    DELETE  /irb/{id} (org.broadinstitute.orsp.api.resources.IrbResource)
    GET     /irb (org.broadinstitute.orsp.api.resources.IrbResource)
    GET     /irb/find/{term} (org.broadinstitute.orsp.api.resources.IrbResource)
    GET     /irb/{id} (org.broadinstitute.orsp.api.resources.IrbResource)
    POST    /irb (org.broadinstitute.orsp.api.resources.IrbResource)
    </pre>

    <h2>Sample Collection APIs</h2>
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

    DELETE  /collection/{id} (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    GET     /collection (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    GET     /collection/find/{term} (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    GET     /collection/{id} (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    POST    /collection (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    POST    /collection/findAll (org.broadinstitute.orsp.api.resources.SampleCollectionResource)
    </pre>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

</body>
</html>