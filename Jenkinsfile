pipeline {
    agent any
​
    environment {
        repo_path = '$(basename $PWD)'
    }
​
    stages {
        stage('sync source code') {
            when{ branch 'master'}
            steps {
                sh "rsync -rva ../${repo_path} ubuntu@10.20.1.216:/home/ubuntu/"
            }
        }
        stage('build') {
            when { branch 'master' }
            steps {
                sh "ssh ubuntu@10.20.1.216 'cd ~/${repo_path} ; mvn clean package -DskipTests'"
            }
        }
        stage('Deploy') {
            when { branch 'master' }
            steps {
                sh "ssh ubuntu@10.20.1.216 'cd ~/${repo_path} ; docker-compose up --build -d'"
            }
        }
        stage('Deployment status') {
            when { branch 'master' }
            steps {
                sh "ssh ubuntu@10.20.1.216 'cd ~/${repo_path} ; sleep 30 ; docker ps'"
            }
        }
    }
