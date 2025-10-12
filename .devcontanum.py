"""
Bug Tracking System - A comprehensive command-line application for managing software bugs
Author: Bug Tracker Team
Date: October 13, 2025
"""

import json
import os
from datetime import datetime
from typing import List, Dict, Optional
import hashlib
import uuid


class User:
    """Represents a user in the bug tracking system"""
    
    def __init__(self, username: str, email: str, password: str, role: str = "developer"):
        self.user_id = str(uuid.uuid4())
        self.username = username
        self.email = email
        self.password_hash = self._hash_password(password)
        self.role = role  # admin, developer, tester, viewer
        self.created_at = datetime.now().isoformat()
    
    def _hash_password(self, password: str) -> str:
        """Hash password using SHA-256"""
        return hashlib.sha256(password.encode()).hexdigest()
    
    def verify_password(self, password: str) -> bool:
        """Verify if provided password matches"""
        return self.password_hash == self._hash_password(password)
    
    def to_dict(self) -> Dict:
        """Convert user object to dictionary"""
        return {
            "user_id": self.user_id,
            "username": self.username,
            "email": self.email,
            "password_hash": self.password_hash,
            "role": self.role,
            "created_at": self.created_at
        }
    
    @staticmethod
    def from_dict(data: Dict) -> 'User':
        """Create user object from dictionary"""
        user = User.__new__(User)
        user.user_id = data["user_id"]
        user.username = data["username"]
        user.email = data["email"]
        user.password_hash = data["password_hash"]
        user.role = data["role"]
        user.created_at = data["created_at"]
        return user


class Bug:
    """Represents a bug report in the system"""
    
    SEVERITIES = ["critical", "high", "medium", "low"]
    STATUSES = ["open", "in_progress", "resolved", "closed", "reopened"]
    PRIORITIES = ["urgent", "high", "normal", "low"]
    
    def __init__(self, title: str, description: str, reporter_id: str, 
                 severity: str = "medium", priority: str = "normal"):
        self.bug_id = str(uuid.uuid4())
        self.title = title
        self.description = description
        self.reporter_id = reporter_id
        self.assignee_id = None
        self.severity = severity if severity in self.SEVERITIES else "medium"
        self.priority = priority if priority in self.PRIORITIES else "normal"
        self.status = "open"
        self.tags = []
        self.comments = []
        self.attachments = []
        self.created_at = datetime.now().isoformat()
        self.updated_at = datetime.now().isoformat()
        self.resolved_at = None
        self.environment = {}
        self.steps_to_reproduce = []
        self.expected_behavior = ""
        self.actual_behavior = ""
    
    def add_comment(self, user_id: str, comment_text: str):
        """Add a comment to the bug"""
        comment = {
            "comment_id": str(uuid.uuid4()),
            "user_id": user_id,
            "text": comment_text,
            "timestamp": datetime.now().isoformat()
        }
        self.comments.append(comment)
        self.updated_at = datetime.now().isoformat()
    
    def update_status(self, new_status: str):
        """Update bug status"""
        if new_status in self.STATUSES:
            self.status = new_status
            self.updated_at = datetime.now().isoformat()
            if new_status == "resolved":
                self.resolved_at = datetime.now().isoformat()
    
    def assign_to(self, user_id: str):
        """Assign bug to a user"""
        self.assignee_id = user_id
        self.updated_at = datetime.now().isoformat()
    
    def add_tag(self, tag: str):
        """Add a tag to the bug"""
        if tag not in self.tags:
            self.tags.append(tag)
            self.updated_at = datetime.now().isoformat()
    
    def set_environment(self, browser: str = "", os: str = "", version: str = ""):
        """Set environment details"""
        self.environment = {
            "browser": browser,
            "os": os,
            "version": version
        }
        self.updated_at = datetime.now().isoformat()
    
    def add_steps(self, steps: List[str]):
        """Add steps to reproduce"""
        self.steps_to_reproduce = steps
        self.updated_at = datetime.now().isoformat()
    
    def to_dict(self) -> Dict:
        """Convert bug object to dictionary"""
        return {
            "bug_id": self.bug_id,
            "title": self.title,
            "description": self.description,
            "reporter_id": self.reporter_id,
            "assignee_id": self.assignee_id,
            "severity": self.severity,
            "priority": self.priority,
            "status": self.status,
            "tags": self.tags,
            "comments": self.comments,
            "attachments": self.attachments,
            "created_at": self.created_at,
            "updated_at": self.updated_at,
            "resolved_at": self.resolved_at,
            "environment": self.environment,
            "steps_to_reproduce": self.steps_to_reproduce,
            "expected_behavior": self.expected_behavior,
            "actual_behavior": self.actual_behavior
        }
    
    @staticmethod
    def from_dict(data: Dict) -> 'Bug':
        """Create bug object from dictionary"""
        bug = Bug.__new__(Bug)
        bug.bug_id = data["bug_id"]
        bug.title = data["title"]
        bug.description = data["description"]
        bug.reporter_id = data["reporter_id"]
        bug.assignee_id = data.get("assignee_id")
        bug.severity = data["severity"]
        bug.priority = data["priority"]
        bug.status = data["status"]
        bug.tags = data.get("tags", [])
        bug.comments = data.get("comments", [])
        bug.attachments = data.get("attachments", [])
        bug.created_at = data["created_at"]
        bug.updated_at = data["updated_at"]
        bug.resolved_at = data.get("resolved_at")
        bug.environment = data.get("environment", {})
        bug.steps_to_reproduce = data.get("steps_to_reproduce", [])
        bug.expected_behavior = data.get("expected_behavior", "")
        bug.actual_behavior = data.get("actual_behavior", "")
        return bug


class BugTracker:
    """Main bug tracking system"""
    
    def __init__(self, data_file: str = "bug_tracker_data.json"):
        self.data_file = data_file
        self.users: Dict[str, User] = {}
        self.bugs: Dict[str, Bug] = {}
        self.current_user: Optional[User] = None
        self.load_data()
    
    def load_data(self):
        """Load data from JSON file"""
        if os.path.exists(self.data_file):
            try:
                with open(self.data_file, 'r') as f:
                    data = json.load(f)
                    self.users = {uid: User.from_dict(u) for uid, u in data.get("users", {}).items()}
                    self.bugs = {bid: Bug.from_dict(b) for bid, b in data.get("bugs", {}).items()}
            except Exception as e:
                print(f"Error loading data: {e}")
    
    def save_data(self):
        """Save data to JSON file"""
        try:
            data = {
                "users": {uid: u.to_dict() for uid, u in self.users.items()},
                "bugs": {bid: b.to_dict() for bid, b in self.bugs.items()}
            }
            with open(self.data_file, 'w') as f:
                json.dump(data, f, indent=2)
        except Exception as e:
            print(f"Error saving data: {e}")
    
    def register_user(self, username: str, email: str, password: str, role: str = "developer") -> bool:
        """Register a new user"""
        if any(u.username == username for u in self.users.values()):
            print("Username already exists!")
            return False
        
        user = User(username, email, password, role)
        self.users[user.user_id] = user
        self.save_data()
        print(f"User {username} registered successfully!")
        return True
    
    def login(self, username: str, password: str) -> bool:
        """Login a user"""
        for user in self.users.values():
            if user.username == username and user.verify_password(password):
                self.current_user = user
                print(f"Welcome back, {username}!")
                return True
        print("Invalid username or password!")
        return False
    
    def logout(self):
        """Logout current user"""
        if self.current_user:
            print(f"Goodbye, {self.current_user.username}!")
            self.current_user = None
    
    def create_bug(self, title: str, description: str, severity: str = "medium", 
                   priority: str = "normal") -> Optional[Bug]:
        """Create a new bug report"""
        if not self.current_user:
            print("Please login first!")
            return None
        
        bug = Bug(title, description, self.current_user.user_id, severity, priority)
        self.bugs[bug.bug_id] = bug
        self.save_data()
        print(f"Bug #{bug.bug_id[:8]} created successfully!")
        return bug
    
    def get_bug(self, bug_id: str) -> Optional[Bug]:
        """Get a bug by ID"""
        return self.bugs.get(bug_id)
    
    def update_bug_status(self, bug_id: str, new_status: str) -> bool:
        """Update bug status"""
        bug = self.get_bug(bug_id)
        if not bug:
            print("Bug not found!")
            return False
        
        bug.update_status(new_status)
        self.save_data()
        print(f"Bug status updated to {new_status}")
        return True
    
    def assign_bug(self, bug_id: str, assignee_username: str) -> bool:
        """Assign bug to a user"""
        bug = self.get_bug(bug_id)
        if not bug:
            print("Bug not found!")
            return False
        
        assignee = next((u for u in self.users.values() if u.username == assignee_username), None)
        if not assignee:
            print("Assignee not found!")
            return False
        
        bug.assign_to(assignee.user_id)
        self.save_data()
        print(f"Bug assigned to {assignee_username}")
        return True
    
    def add_comment(self, bug_id: str, comment_text: str) -> bool:
        """Add comment to a bug"""
        if not self.current_user:
            print("Please login first!")
            return False
        
        bug = self.get_bug(bug_id)
        if not bug:
            print("Bug not found!")
            return False
        
        bug.add_comment(self.current_user.user_id, comment_text)
        self.save_data()
        print("Comment added successfully!")
        return True
    
    def list_bugs(self, status: Optional[str] = None, severity: Optional[str] = None) -> List[Bug]:
        """List bugs with optional filters"""
        bugs = list(self.bugs.values())
        
        if status:
            bugs = [b for b in bugs if b.status == status]
        if severity:
            bugs = [b for b in bugs if b.severity == severity]
        
        return bugs
    
    def search_bugs(self, keyword: str) -> List[Bug]:
        """Search bugs by keyword in title or description"""
        keyword = keyword.lower()
        return [b for b in self.bugs.values() 
                if keyword in b.title.lower() or keyword in b.description.lower()]
    
    def get_my_bugs(self) -> List[Bug]:
        """Get bugs reported by current user"""
        if not self.current_user:
            return []
        return [b for b in self.bugs.values() if b.reporter_id == self.current_user.user_id]
    
    def get_assigned_bugs(self) -> List[Bug]:
        """Get bugs assigned to current user"""
        if not self.current_user:
            return []
        return [b for b in self.bugs.values() if b.assignee_id == self.current_user.user_id]
    
    def get_statistics(self) -> Dict:
        """Get bug statistics"""
        stats = {
            "total_bugs": len(self.bugs),
            "open": len([b for b in self.bugs.values() if b.status == "open"]),
            "in_progress": len([b for b in self.bugs.values() if b.status == "in_progress"]),
            "resolved": len([b for b in self.bugs.values() if b.status == "resolved"]),
            "closed": len([b for b in self.bugs.values() if b.status == "closed"]),
            "critical": len([b for b in self.bugs.values() if b.severity == "critical"]),
            "high": len([b for b in self.bugs.values() if b.severity == "high"]),
            "medium": len([b for b in self.bugs.values() if b.severity == "medium"]),
            "low": len([b for b in self.bugs.values() if b.severity == "low"])
        }
        return stats
    
    def print_bug_details(self, bug: Bug):
        """Print detailed bug information"""
        reporter = self.users.get(bug.reporter_id)
        assignee = self.users.get(bug.assignee_id) if bug.assignee_id else None
        
        print("\n" + "="*60)
        print(f"Bug ID: {bug.bug_id[:8]}")
        print(f"Title: {bug.title}")
        print(f"Description: {bug.description}")
        print(f"Reporter: {reporter.username if reporter else 'Unknown'}")
        print(f"Assignee: {assignee.username if assignee else 'Unassigned'}")
        print(f"Severity: {bug.severity.upper()}")
        print(f"Priority: {bug.priority.upper()}")
        print(f"Status: {bug.status.upper()}")
        print(f"Tags: {', '.join(bug.tags) if bug.tags else 'None'}")
        print(f"Created: {bug.created_at}")
        print(f"Updated: {bug.updated_at}")
        
        if bug.environment:
            print(f"\nEnvironment:")
            for key, value in bug.environment.items():
                if value:
                    print(f"  {key}: {value}")
        
        if bug.steps_to_reproduce:
            print(f"\nSteps to Reproduce:")
            for i, step in enumerate(bug.steps_to_reproduce, 1):
                print(f"  {i}. {step}")
        
        if bug.comments:
            print(f"\nComments ({len(bug.comments)}):")
            for comment in bug.comments:
                user = self.users.get(comment['user_id'])
                username = user.username if user else 'Unknown'
                print(f"  [{username}] {comment['text']}")
        
        print("="*60 + "\n")


def main():
    """Main function to run the bug tracker CLI"""
    tracker = BugTracker()
    
    print("="*60)
    print("Welcome to BugTrack - Bug Tracking System")
    print("="*60)
    
    while True:
        if not tracker.current_user:
            print("\n1. Register")
            print("2. Login")
            print("3. Exit")
            choice = input("\nSelect option: ").strip()
            
            if choice == "1":
                username = input("Username: ").strip()
                email = input("Email: ").strip()
                password = input("Password: ").strip()
                role = input("Role (developer/tester/admin): ").strip() or "developer"
                tracker.register_user(username, email, password, role)
            
            elif choice == "2":
                username = input("Username: ").strip()
                password = input("Password: ").strip()
                tracker.login(username, password)
            
            elif choice == "3":
                print("Goodbye!")
                break
        
        else:
            print(f"\nLogged in as: {tracker.current_user.username} ({tracker.current_user.role})")
            print("\n1. Create Bug")
            print("2. View All Bugs")
            print("3. View My Bugs")
            print("4. View Assigned Bugs")
            print("5. Search Bugs")
            print("6. View Bug Details")
            print("7. Update Bug Status")
            print("8. Assign Bug")
            print("9. Add Comment")
            print("10. View Statistics")
            print("11. Logout")
            
            choice = input("\nSelect option: ").strip()
            
            if choice == "1":
                title = input("Bug Title: ").strip()
                description = input("Description: ").strip()
                severity = input("Severity (critical/high/medium/low): ").strip() or "medium"
                priority = input("Priority (urgent/high/normal/low): ").strip() or "normal"
                tracker.create_bug(title, description, severity, priority)
            
            elif choice == "2":
                bugs = tracker.list_bugs()
                print(f"\nTotal Bugs: {len(bugs)}")
                for bug in bugs:
                    print(f"  [{bug.bug_id[:8]}] {bug.title} - {bug.severity}/{bug.status}")
            
            elif choice == "3":
                bugs = tracker.get_my_bugs()
                print(f"\nMy Bugs: {len(bugs)}")
                for bug in bugs:
                    print(f"  [{bug.bug_id[:8]}] {bug.title} - {bug.severity}/{bug.status}")
            
            elif choice == "4":
                bugs = tracker.get_assigned_bugs()
                print(f"\nAssigned to Me: {len(bugs)}")
                for bug in bugs:
                    print(f"  [{bug.bug_id[:8]}] {bug.title} - {bug.severity}/{bug.status}")
            
            elif choice == "5":
                keyword = input("Search keyword: ").strip()
                bugs = tracker.search_bugs(keyword)
                print(f"\nFound {len(bugs)} bugs:")
                for bug in bugs:
                    print(f"  [{bug.bug_id[:8]}] {bug.title} - {bug.severity}/{bug.status}")
            
            elif choice == "6":
                bug_id = input("Bug ID: ").strip()
                bug = tracker.get_bug(bug_id)
                if bug:
                    tracker.print_bug_details(bug)
            
            elif choice == "7":
                bug_id = input("Bug ID: ").strip()
                status = input("New Status (open/in_progress/resolved/closed): ").strip()
                tracker.update_bug_status(bug_id, status)
            
            elif choice == "8":
                bug_id = input("Bug ID: ").strip()
                assignee = input("Assignee Username: ").strip()
                tracker.assign_bug(bug_id, assignee)
            
            elif choice == "9":
                bug_id = input("Bug ID: ").strip()
                comment = input("Comment: ").strip()
                tracker.add_comment(bug_id, comment)
            
            elif choice == "10":
                stats = tracker.get_statistics()
                print("\n" + "="*40)
                print("BUG STATISTICS")
                print("="*40)
                print(f"Total Bugs: {stats['total_bugs']}")
                print(f"\nBy Status:")
                print(f"  Open: {stats['open']}")
                print(f"  In Progress: {stats['in_progress']}")
                print(f"  Resolved: {stats['resolved']}")
                print(f"  Closed: {stats['closed']}")
                print(f"\nBy Severity:")
                print(f"  Critical: {stats['critical']}")
                print(f"  High: {stats['high']}")
                print(f"  Medium: {stats['medium']}")
                print(f"  Low: {stats['low']}")
                print("="*40)
            
            elif choice == "11":
                tracker.logout()


if __name__ == "__main__":
    main()
