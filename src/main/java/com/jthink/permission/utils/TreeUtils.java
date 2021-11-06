package com.jthink.permission.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jthink.cms.entity.JthinkTree;
import com.jthink.permission.entity.AuthTree;

public class TreeUtils {

	protected TreeUtils() {

	}

	public static <T> JthinkTree<T> buildTree(List<JthinkTree<T>> nodes) {
		if (nodes == null) {
			return null;
		}
		List<JthinkTree<T>> topNodes = new ArrayList<>();
		nodes.forEach(children -> {
			String pid = children.getParentId();
			if (pid == null || "0".equals(pid)) {
				children.setHasParent(true);
				topNodes.add(children);
				return;
			}
			for (JthinkTree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
					return;
				}
			}
		});

		JthinkTree<T> root = new JthinkTree<>();
		root.setId("0");
		root.setParentId("");
		root.setHasParent(false);
		root.setChildren(true);
		root.setChecked(true);
		root.setChildren(topNodes);
		root.setTitle("根节点");
		Map<String, Object> state = new HashMap<>(16);
		state.put("opened", true);
		root.setState(state);
		return root;
	}

	public static <T> AuthTree<T> build(List<AuthTree<T>> nodes) {
		if (nodes == null) {
			return null;
		}
		List<AuthTree<T>> topNodes = new ArrayList<>();
		nodes.forEach(children -> {
			String pid = children.getParentId();
			if (pid == null || "0".equals(pid)) {
				children.setHasParent(true);
				topNodes.add(children);
				return;
			}
			for (AuthTree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
					return;
				}
			}
		});

		AuthTree<T> root = new AuthTree<>();
		root.setId("0");
		root.setParentId("");
		root.setHasParent(false);
		root.setChildren(true);
		root.setChecked(true);
		root.setChildren(topNodes);
		root.setTitle("根节点");
		Map<String, Object> state = new HashMap<>(16);
		state.put("opened", true);
		root.setState(state);
		return root;
	}

	public static <T> List<AuthTree<T>> buildList(List<AuthTree<T>> nodes, String idParam) {
		if (nodes == null) {
			return new ArrayList<>();
		}
		List<AuthTree<T>> topNodes = new ArrayList<>();
		nodes.forEach(children -> {
			String pid = children.getParentId();
			if (pid == null || idParam.equals(pid)) {
				topNodes.add(children);
				return;
			}
			nodes.forEach(parent -> {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
				}
			});
		});
		return topNodes;
	}
}