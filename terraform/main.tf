provider "aws" {
  region     = "us-east-1"
  access_key = var.AWS_ACCESS_KEY
  secret_key = var.AWS_SECRET_KEY
}

# Create ECR Repository
resource "aws_ecr_repository" "myapp_repo" {
  name = "myapp-ecr-repo"
}

# Create EC2 Security Group
resource "aws_security_group" "myapp_sg" {
  name        = "myapp-security-group"
  description = "Allow inbound traffic"
  ingress {
    from_port   = 8081
    to_port     = 8081
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Create EC2 Instance
resource "aws_instance" "myapp_ec2" {
  ami           = "ami-0abcdef1234567890"  # Replace with a valid AMI ID
  instance_type = "t2.micro"
  key_name      = "jenkins-ssh-key"
  security_groups = [aws_security_group.myapp_sg.name]

  tags = {
    Name = "MyApp-EC2"
  }
}
