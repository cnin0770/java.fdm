node {
    stage 'Clone the project'
    git 'https://git.fdmgroup.com/Chuan.Ning/barrieroptionmonitor.git'

    dir('.') {
        stage("Compilation and Analysis") {
            parallel 'Compilation': {
                sh "./mvnw clean install -DskipTests"
            }, 'Static Analysis': {
                stage("Checkstyle") {
                    sh "./mvnw checkstyle:checkstyle"

                    step([$class                   : 'CheckStylePublisher',
                          canRunOnFailed           : true,
                          defaultEncoding          : '',
                          healthy                  : '100',
                          pattern                  : '**/target/checkstyle-result.xml',
                          unHealthy                : '90',
                          useStableBuildAsReference: true
                    ])
                }
            }
        }

        stage("Tests") {
            stage("Running tests") {
                step([$class: 'JUnitResultArchiver', allowEmptyResults: true, testResults:
                        '**/target/surefire-reports/TEST-*UnitTest.xml, healthScaleFactor: 1.0, fingerprint: ture'])
            }
        }
    }
}