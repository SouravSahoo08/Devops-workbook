pipeline{
    agent any
    
    tools{
        maven 'MAVEN3.8.7'
    }

    environment{
        PROJECT_DIR = 'demo'

        registryUrl = 'https://650251730135.dkr.ecr.us-east-1.amazonaws.com'
        imageName = '650251730135.dkr.ecr.us-east-1.amazonaws.com/demo-app-ci'
        registryCredentials = 'ecr:us-east-1:aws-jenkins-creds'
    }

    stages{
        stage("Fetch Code from Repo"){
            steps{
                git branch: 'ci-pipeline', url: 'https://github.com/SouravSahoo08/Devops-workbook.git'
            }
        }   
        
        stage("Unit test"){
            steps{
                dir("${env.PROJECT_DIR}"){
                    sh 'mvn clean test'
                }
            }
        }   
        
        stage("Build project"){
            steps{
                dir("${env.PROJECT_DIR}"){
                    sh 'mvn clean install -DskipTests'
                }
            }
        }   
        
        stage("Snyk scan"){
            steps{
                dir("${env.PROJECT_DIR}"){

                    // make mvnw file executable for snyk scan
                    
                    echo "running snyk test"

                    sh 'chmod +x ./mvnw'

                    snykSecurity(
                        snykInstallation: 'snyk@latest',
                        snykTokenId: 'snyk-api-token',
                        failOnIssues: false,
                        monitorProjectOnBuild: true
                    )
                }
            }
        }   
        
        stage("Build image"){
            steps{
                dir("${env.PROJECT_DIR}"){
                    script{
                        dockerImage = docker.build("${env.imageName}" + ":$BUILD_NUMBER")
                    }
                }
            }
        }   
        
        stage("Push image to registry"){
            steps{
                dir("${env.PROJECT_DIR}"){
                    script{
                        docker.withRegistry("${env.registryUrl}", "${env.registryCredentials}"){
                            dockerImage.push("$BUILD_NUMBER")
                            dockerImage.push("latest")
                        }
                    }
                }
            }
        }  

        stage("clean workspace"){
            steps{
                sh 'docker rmi -f $(docker images -a -q)'
            }
        }
    }
}