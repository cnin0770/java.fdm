pipeline {
    agent any

    stages {
        stage ('Initialize') {
            steps {
                echo 'Cleaning the target directory/build area'
                sh 'mvn clean -f "backend"'
            }
        }
        stage ('Validate') {
            steps {
                echo 'Validating the project & dependencies are downloaded.'
            }
        }
        stage('Compile') {
            steps {
                echo 'Compiling Source code ...'
                sh 'mvn compile -f "backend"'
            }
        }

        stage('Test') {
            steps {
                echo 'Running all tests.'
                sh 'mvn test -f "backend"'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging'
                sh 'mvn package -B -T 2.0C -DskipTests -f "backend"'
            }
        }


        stage('Deploy') {
            steps {
                echo 'Deploying....'
                script {
                    docker.withRegistry('','Docker_Access_Token') {
                        def RatImage = docker.build("my-image: ${env.Build_ID}")
                        // Push the container to the custom Registry
                        RatImage.push()
                    }
                }

            }
        }

    }
}
