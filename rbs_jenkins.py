import os
import sys
import xml.etree.ElementTree as ET
import jenkins

def main():

    username = sys.argv[1]
    password = sys.argv[2]

    os.system("easy_install --user python-jenkins")
    os.system("easy_install --user -U six")

    for path, subdirs, files in os.walk("./jenkins"):
         for file in files:
            job_name = file[:file.index(".xml")]
            file_path = path + "/" + file
            job_xml = xml_to_string(file_path)

            server = jenkins.Jenkins('http://localhost:8080', username, password)
            server.create_job(job_name, job_xml)
            print "CREATING JOB: " + job_name 
            server.build_job(job_name)
            print "BUILDING JOB: " + job_name 



def xml_to_string(file):
    tree = ET.parse(file)
    root = tree.getroot()
    return ET.tostring(root, encoding='utf8', method='xml').decode()

if __name__ == "__main__":
    main()
    