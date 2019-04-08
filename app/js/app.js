$(document).ready(function() {

    //var API_URL = "http://localhost:8084/alligator/";
    //var API_URL = "http://143.93.114.135/alligator/";
    var API_URL = "https://java-dev.rgzm.de/alligator/";

    // elements
    $("#matrix-div").hide();
    $("#rdffile-div").hide();
    $("#cypherfile-div").hide();
    $("#amtfile-div").hide();
    $("#amtrepo-div").hide();
    $("#timeline-div").hide();
    $("#graph-div").hide();
    $("#rdf-div").hide();
    $("#refresh-div").hide();
    $("#legend-div").hide();
    $("#legend2-div").hide();
    $("#wrapper-div").hide();

    // The event listener for the file upload
    document.getElementById('txtFileUpload').addEventListener('change', upload, false);
    // Method that checks that the browser supports the HTML5 File API
    function browserSupportFileUpload() {
        var isCompatible = false;
        if (window.File && window.FileReader && window.FileList && window.Blob) {
            isCompatible = true;
        }
        return isCompatible;
    }
    // Method that reads and processes the selected file
    function upload(evt) {
        if (!browserSupportFileUpload()) {
            alert('The File APIs are not fully supported in this browser!');
        } else {
            var file = evt.target.files[0];
            var reader = new FileReader();
            reader.readAsText(file);
            reader.onload = function(event) {
                var csvData = event.target.result;
                data = $.csv.toArrays(csvData);
                var selValue = $('input[name=alligator]:checked').val();
                $("#loading").html("loading...");
                $.ajax({
                    type: "POST",
                    url: API_URL + selValue + "/",
                    data: $("#startfixed").val() + "," + $("#endfixed").val() + ";" + csvData,
                    contentType: 'text/plain',
                    error: function(jqXHR, textStatus, errorThrown) {
                        alert(errorThrown);
                    },
                    success: function(response) {
                        console.log(response);
                        $("#refresh-div").show();
                        $("#wrapper-div").show();
                        $("#legend-div").show();
                        $("#legend2-div").show();
                        $("#upload-div").hide();
                        $("#info-div").hide();
                        if (selValue === "matrixdist") {
                            var html = "<tr>";
                            for (var item in response[0]) {
                                html += "<th>" + response[0][item] + "</th>";
                            }
                            response.splice(0, 1);
                            html += "</tr>";
                            for (var row in response) {
                                html += "<tr>";
                                for (var item in response[row]) {
                                    html += "<td>" + response[row][item] + "</td>";
                                }
                                html += "</tr>";
                            }
                            $('#matrixtable').css('width', '100%');
                            $("#matrixtable").append(html);
                            $("#matrix-div").show();
                        }
                        if (selValue === "matrixallen") {
                            var html = "<tr>";
                            for (var item in response[0]) {
                                html += "<th>" + response[0][item] + "</th>";
                            }
                            response.splice(0, 1);
                            html += "</tr>";
                            for (var row in response) {
                                html += "<tr>";
                                for (var item in response[row]) {
                                    html += "<td>" + response[row][item] + "</td>";
                                }
                                html += "</tr>";
                            }
                            $('#matrixtable').css('width', '100%');
                            $("#matrixtable").append(html);
                            $("#matrix-div").show();
                        }
                        if (selValue === "timeline") {
                            // DOM element where the Timeline will be attached
                            var container = document.getElementById('timeline-div');
                            // Create a DataSet (allows two way data-binding)
                            var items = new vis.DataSet(response);
                            // Configuration for the Timeline
                            var options = {};
                            // Create a Timeline
                            var timeline = new vis.Timeline(container, items, options);
                            $('.vis-time-axis.vis-foreground').hide();
                            $("#timeline-div").show();
                        }
                        if (selValue === "graph") {
                            $('#graph-div').width(1200);
                            $('#graph-div').height(800);
                            var container = document.getElementById('graph-div');
                            var data = {
                                nodes: new vis.DataSet(response.nodes),
                                edges: new vis.DataSet(response.edges)
                            };
                            var options = {};
                            var network = new vis.Network(container, data, options);
                            $("#graph-div").show();
                        }
                        if (selValue === "turtle") {
                            $("#rdffile-div").show();
                            $("#rdffile-div").html("");
                            $("#rdffile-div").html("<textarea id='rdf' style='width:1px;height:auto'></textarea>");
                            var turtle = CodeMirror.fromTextArea(document.getElementById("rdf"), {
                                mode: "text/turtle",
                                matchBrackets: true,
                                lineNumbers: true,
                            });
                            turtle.setValue(response);
                            turtle.setOption("theme", "darcula");
                        }
                        if (selValue === "cypher") {
                            $("#cypherfile-div").show();
                            $("#cypherfile-div").html("");
                            $("#cypherfile-div").html("<textarea id='cypher' style='width:1px;height:auto'></textarea>");
                            var cypher = CodeMirror.fromTextArea(document.getElementById("cypher"), {
                                mode: "application/x-cypher-query",
                                lineNumbers: true
                            });
                            cypher.setValue(response);
                            cypher.setOption("theme", "darcula");
                        }
                        if (selValue === "amt") {
                            $("#amtfile-div").show();
                            $("#amtfile-div").html("");
                            $("#amtfile-div").html("<textarea id='rdf' style='width:1px;height:auto'></textarea>");
                            var turtle = CodeMirror.fromTextArea(document.getElementById("rdf"), {
                                mode: "text/turtle",
                                matchBrackets: true,
                                lineNumbers: true,
                            });
                            turtle.setValue(response);
                            turtle.setOption("theme", "darcula");
                        }
                        if (selValue === "amtrepo") {
                            $("#amtrepo-div").show();
                            $("#amtrepo-div").html("");
                            $("#amtrepo-div").html("<iframe src='amt-time/index.html?repo=" + response + "' width='1200px' height='800px'></iframe>");
                        }
                        if (selValue === "turtlefile") {
                            $("#rdf-div").html("");
                            $("#rdf-div").html("<iframe src='http://visualrdf.dernettekleinenerd.de/?url=http://143.93.114.135/alligator-files/" + response + "' width='1200px' height='800px'></iframe>");
                            $("#rdf-div").show();
                        }
                        $("#loading").hide();
                    }
                });
            };
            reader.onerror = function() {
                alert('Unable to read ' + file.fileName);
            };
        }
    }
});
