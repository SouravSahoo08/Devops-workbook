pipeline{
    agent any
    
    tools{
        maven 'MAVEN3.8.4'
    }

    environment{
        PROJECT_DIR = 'demo'
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
                    sh 'chmod +x ./mvnw'

                    snykSecurity(
                        snykInstallation: 'snyk@latest',
                        snykTokenId: 'snyk-api-token',
                        failOnIssues: false
                        monitorProjectOnBuild: true
                    )
                }
            }
        }   
        
        // stage("Build image"){
        //     steps{
        //         dir("${env.PROJECT_DIR}"){
                
        //         }
        //     }
        // }   
        // stage("Push image to registry"){
        //     steps{
        //         dir("${env.PROJECT_DIR}"){
                
        //         }
        //     }
        // }   
    }
}